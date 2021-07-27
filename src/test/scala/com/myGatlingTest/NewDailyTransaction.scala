package com.myGatlingTest

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.commons.validation.Validation
import scala.concurrent.duration._
import com.myGatlingTest.Module.Login._
import com.myGatlingTest.Module.Leave._
import com.myGatlingTest.Module.Claim._
import com.myGatlingTest.Module.Profile._
import com.myGatlingTest.Module.Workflow._
import com.myGatlingTest.Module.Profile.Report._
import com.myGatlingTest.Module.Leave.Report._
import com.myGatlingTest.Module.Claim.Report._
import com.myGatlingTest.Module.Document._
import com.myGatlingTest.Module.Service._

class NewDailyTransaction extends Simulation{


    val httpProtocol = http
      .baseUrl("https://orisoftst.orisoftsaas.com")
      .disableFollowRedirect

  val loginFeeder8000 = csv("data/EMP8000.csv").random
  var loginFeeder = csv("data/ESSEMPLogin1000csv").queue
  var loginFeeder1 = csv("data/ESSEMPLogin1001-2000.csv").queue
  var loginFeeder2 = csv("data/ESSEMPLogin2001-3000.csv").queue
  var loginFeeder3 = csv("data/ESSEMPLogin3001-4000.csv").queue
  var loginFeeder4 = csv("data/ESSEMPLogin4001-5000.csv").queue
  var loginFeeder5 = csv("data/ESSEMPLogin5001-6000.csv").queue
  var loginFeeder6 = csv("data/ESSEMPLogin6001-7000.csv").queue
  var loginFeeder7 = csv("data/ESSEMPLogin7001-7500.csv").queue

  var loginClaimFeeder1 = csv("data/ESSEMPLogin-Claim1-255.csv").random
  var loginClaimFeeder2 = csv("data/ESSEMPLogin-Claim200.csv").queue

  val loginSupFeeder1 = csv("data/superior_Credential.csv").random
  val loginSupFeeder2 = csv("data/superior_Credential.csv").queue
  val loginSupFeeder3 = csv("data/superior_Credential.csv").queue
  val loginSupFeeder4 = csv("data/superior_Credential.csv").queue
  val loginSupFeeder5 = csv("data/superior_Credential.csv").queue
  val loginSupFeeder6 = csv("data/superior_Credential.csv").queue
  val loginSupFeeder7 = csv("data/superior_Credential.csv").queue
  val loginSupFeeder8 = csv("data/superior_Credential.csv").queue
  val loginSupFeeder9 = csv("data/superior_Credential.csv").queue
  val loginSupFeeder10 = csv("data/superior_Credential.csv").queue

  val loginHrFeeder = csv("data/hr_Credential.csv").random

    //Login Employee
    //val loginFeeder = feed(loginFeeder8000).exec(Login.loginProcess).exec(Login.loadDashboard)

    //Login Superior
    //val loginSup = feed(loginSupFeeder1).exec(Login.loginProcess).exec(Login.loadDashboard)

    //Login HR
    //val loginHr = feed(loginHrFeeder).exec(Login.loginProcess).exec(Login.loadDashboard)

  //No of Users
  val EMPNo = 24
  val SUPNo = 24
  val EMPNoClaim = 24
  val HRNo = 24

  //Duration
  val scriptDuration = 60

  //Login Employee
  val login = feed(loginFeeder8000).exec(Login.loginProcess).exec(Login.loadDashboard)
  val login1 = feed(loginFeeder1).exec(Login.loginProcess).exec(Login.loadDashboard)
  val login2 = feed(loginFeeder2).exec(Login.loginProcess).exec(Login.loadDashboard)
  val login3 = feed(loginFeeder3).exec(Login.loginProcess).exec(Login.loadDashboard)
  val login4 = feed(loginFeeder4).exec(Login.loginProcess).exec(Login.loadDashboard)
  val login5 = feed(loginFeeder5).exec(Login.loginProcess).exec(Login.loadDashboard)
  val login6 = feed(loginFeeder6).exec(Login.loginProcess).exec(Login.loadDashboard)
  val login7 = feed(loginFeeder7).exec(Login.loginProcess).exec(Login.loadDashboard)

  //Login Employee Claim
  val loginClaim = feed(loginClaimFeeder1).exec(Login.loginProcess).exec(Login.loadDashboard)
  val loginClaim2 = feed(loginClaimFeeder2).exec(Login.loginProcess).exec(Login.loadDashboard)

  //Login Superior
  val loginSup = feed(loginSupFeeder1).exec(Login.loginProcess).exec(Login.loadDashboard)
  val loginSup1 = feed(loginSupFeeder2).exec(Login.loginProcess).exec(Login.loadDashboard)
  val loginSup2 = feed(loginSupFeeder3).exec(Login.loginProcess).exec(Login.loadDashboard)
  val loginSup3 = feed(loginSupFeeder4).exec(Login.loginProcess).exec(Login.loadDashboard)
  val loginSup4 = feed(loginSupFeeder5).exec(Login.loginProcess).exec(Login.loadDashboard)
  val loginSup5 = feed(loginSupFeeder6).exec(Login.loginProcess).exec(Login.loadDashboard)
  val loginSup6 = feed(loginSupFeeder7).exec(Login.loginProcess).exec(Login.loadDashboard)
  val loginSup7 = feed(loginSupFeeder8).exec(Login.loginProcess).exec(Login.loadDashboard)
  val loginSup8 = feed(loginSupFeeder9).exec(Login.loginProcess).exec(Login.loadDashboard)
  val loginSup9 = feed(loginSupFeeder10).exec(Login.loginProcess).exec(Login.loadDashboard)

  //Login HR
  val loginHr = feed(loginHrFeeder).exec(Login.loginProcess).exec(Login.loadDashboard)

  //Emp
  //Apply Leave Execution
  val applyLeaveSaveDraft = exec(ESSEMPApplyLeave.loadLeaveForm,ESSEMPApplyLeave.saveLeaveDraft,ESSEMPApplyLeave.leaveLogout)
  val applyLeaveDeleteDraft = exec(ESSEMPApplyLeave.loadLeaveForm,ESSEMPApplyLeave.getSavedDraft,ESSEMPApplyLeave.deleteSavedDraft,ESSEMPApplyLeave.loadLeaveForm,ESSEMPApplyLeave.leaveLogout)
  val applyLeaveSubmit = exec(ESSEMPApplyLeave.loadLeaveForm,ESSEMPApplyLeave.submitLeave,ESSEMPApplyLeave.leaveLogout)
  val applyLeaveReset = exec(ESSEMPApplyLeave.loadLeaveForm,ESSEMPApplyLeave.leaveLogout)
  val applyLeaveAttachDraft = exec(ESSEMPApplyLeave.loadLeaveForm,ESSEMPApplyLeave.saveLeaveDraft,ESSEMPApplyLeave.leaveLogout)

  //cancel Leave
  val CancelLeave =  exec(ESSEMPCancelLeave.loadApplication,ESSEMPCancelLeave.displayList,ESSEMPCancelLeave.submitCancel,ESSEMPCancelLeave.logout)

  //ESS EMP My Leave Entitlement
  val empMyLeaveEntitlement = exec(ESSEMPMyLeaveEntitlement.loadApplication,ESSEMPMyLeaveEntitlement.searchEntitle,ESSEMPMyLeaveEntitlement.logout)

  //ESS EMP My Leave Application
  val empCheckMyLeaveApplication = exec(ESSEMPMyLeaveApplication.loadApplication,ESSEMPMyLeaveApplication.searchMLADate,ESSEMPMyLeaveApplication.displayList,ESSEMPMyLeaveApplication.logout)
  val empWithdrawLeaveMyLeaveApplication = exec(ESSEMPMyLeaveApplication.loadApplication,ESSEMPMyLeaveApplication.searchMLADate,ESSEMPMyLeaveApplication.displayList,ESSEMPMyLeaveApplication.submitWithdraw,ESSEMPMyLeaveApplication.logout)

  //Apply Claim
  val empApplyClaimSaveDraft = exec(ESSEMPApplyClaim.loadApplication,ESSEMPApplyClaim.chooseOpt,ESSEMPApplyClaim.uploadProcess,ESSEMPApplyClaim.submitDraftClaim,ESSEMPApplyClaim.logout1)
  val empApplyClaimDeleteDraft = exec(ESSEMPApplyClaim.loadApplication,ESSEMPApplyClaim.delDraft,ESSEMPApplyClaim.logout1)
  val empApplyClaimSubmit = exec(ESSEMPApplyClaim.loadApplication,ESSEMPApplyClaim.submitClaimLeave,ESSEMPApplyClaim.logout2)
  val empApplyClaimReset = exec(ESSEMPApplyClaim.loadApplication,ESSEMPApplyClaim.chooseOpt ,ESSEMPApplyClaim.uploadProcess,ESSEMPApplyClaim.resetClaim,ESSEMPApplyClaim.logout1)
  val empApplyClaimAttach = exec(ESSEMPApplyClaim.loadApplication,ESSEMPApplyClaim.chooseOpt,ESSEMPApplyClaim.uploadProcess,ESSEMPApplyClaim.submitClaim,ESSEMPApplyClaim.logout2)

  //ESS EMP My Claim Entitlement
  val empMyClaimEntitlement = exec(ESSEMPMyClaimEntitlement.loadApplication,ESSEMPMyClaimEntitlement.searchEntitle,ESSEMPMyClaimEntitlement.logout)

  //ESS EMP MY Claim Application
  val checkClaimApplicationex = exec(ESSEMPMyClaimApplication.loadMyClaimApplication,ESSEMPMyClaimApplication.selectClaimApplication,ESSEMPMyClaimApplication.logout)
  val withdrawClaimApplicationex = exec(ESSEMPMyClaimApplication.loadMyClaimApplication,ESSEMPMyClaimApplication.selectClaimApplication,ESSEMPMyClaimApplication.withdrawClaim,ESSEMPMyClaimApplication.logout)

  //ESSEMP My Profile
  val myProfileReset = exec(ESSEMPMyProfile.loadApplication,ESSEMPMyProfile.clickEdit,ESSEMPMyProfile.logout)
  val myProfileSaveDraft = exec(ESSEMPMyProfile.loadApplication,ESSEMPMyProfile.saveDraft,ESSEMPMyProfile.logout)
  val myProfileSubmitDraft = exec(ESSEMPMyProfile.loadApplication,ESSEMPMyProfile.clickDraft,ESSEMPMyProfile.submitDraft,ESSEMPMyProfile.logout)
  val myProfileSubmit = exec(ESSEMPMyProfile.loadApplication,ESSEMPMyProfile.submitProfile,ESSEMPMyProfile.logout)
  val myProfileDeleteDraft = exec(ESSEMPMyProfile.loadApplication,ESSEMPMyProfile.clickDraft,ESSEMPMyProfile.deleteDraft,ESSEMPMyProfile.logout)

  //My Document
  val myDocumentCreateex = exec(ESSEMPMyDocument.loadMyDocument,ESSEMPMyDocument.clickCreateButton,ESSEMPMyDocument.submitCreateDocument,ESSEMPMyDocument.logout)
  val myDocumentDownloadex = exec(ESSEMPMyDocument.loadMyDocument,ESSEMPMyDocument.searchItem,ESSEMPMyDocument.clickDownloadItem,ESSEMPMyDocument.logout)
  val myDocumentDeleteex = exec(ESSEMPMyDocument.loadMyDocument,ESSEMPMyDocument.searchItem,ESSEMPMyDocument.deleteDocumentItem,ESSEMPMyDocument.logout)

  //My Service
  val MyServiceHistoryex = exec(ESSEMPMyServiceHistory.loadServicePage,ESSEMPMyServiceHistory.searchService,ESSEMPMyServiceHistory.logout)

  //My Workflow - View Leave
  val empWorkFlowViewLeave = exec(ESSSUPMyWorkflowViewLeaveApplication.loadApplication,ESSSUPMyWorkflowViewLeaveApplication.searchLA,ESSSUPMyWorkflowViewLeaveApplication.viewList,ESSSUPMyWorkflowViewLeaveApplication.logout)
  val empWorkFlowWithdrawLeave = exec(ESSSUPMyWorkflowWithdrawLeave.loadApplication,ESSSUPMyWorkflowWithdrawLeave.searchCritLAPend,ESSSUPMyWorkflowWithdrawLeave.withdrawLA,ESSSUPMyWorkflowWithdrawLeave.logout)
  val empWorkFlowWithdrawCancelLeave = exec(ESSEMPMyWorkflowWithdrawCancelledLeave.loadApplication,ESSEMPMyWorkflowWithdrawCancelledLeave.searchCritLCPend,ESSEMPMyWorkflowWithdrawCancelledLeave.withdrawLC,ESSEMPMyWorkflowWithdrawCancelledLeave.logout)

  //My Workflow - View Claim
  val empMyWorkflowViewClaim = exec(ESSEMPMyWorkflowViewClaim.loadApplication,ESSEMPMyWorkflowViewClaim.searchCritCA,ESSEMPMyWorkflowViewClaim.viewCA,ESSEMPMyWorkflowViewClaim.logout)
  val empMyWorkflowWithdrawClaim = exec(ESSEMPMyWorkflowWithdrawClaim.loadApplication,ESSEMPMyWorkflowWithdrawClaim.searchCritCAPend,ESSEMPMyWorkflowWithdrawClaim.withdrawCA,ESSEMPMyWorkflowWithdrawClaim.logout)

  //Sup Leave
  val supMyWorkflowLeaveSave = exec(ESSSUPMyWorkflowLeave.loadApplication,ESSSUPMyWorkflowLeave.searchCrit,ESSSUPMyWorkflowLeave.saveLeave,ESSSUPMyWorkflowLeave.logout)
  val supMyWorkflowLeaveReset = exec(ESSSUPMyWorkflowLeave.loadApplication,ESSSUPMyWorkflowLeave.searchCrit,ESSSUPMyWorkflowLeave.resetLeave,ESSSUPMyWorkflowLeave.logout)
  val supMyWorkflowLeaveReject = exec(ESSSUPMyWorkflowLeave.loadApplication,ESSSUPMyWorkflowLeave.searchCrit,ESSSUPMyWorkflowLeave.rejectLeave,ESSSUPMyWorkflowLeave.logout)
  val supMyWorkflowLeaveApprove = exec(ESSSUPMyWorkflowLeave.loadApplication,ESSSUPMyWorkflowLeave.searchCrit,ESSSUPMyWorkflowLeave.approveLeave,ESSSUPMyWorkflowLeave.logout)

  //Sup Claim
  val supMyWorkflowClaimSave = exec(ESSSUPMyWorkflowClaim.loadApplication,ESSSUPMyWorkflowClaim.searchClaimApplication,ESSSUPMyWorkflowClaim.clickFirstClaimRow,ESSSUPMyWorkflowClaim.claimSaveForm,ESSSUPMyWorkflowClaim.logout)
  val supMyWorkflowClaimReset = exec(ESSSUPMyWorkflowClaim.loadApplication,ESSSUPMyWorkflowClaim.searchClaimApplication,ESSSUPMyWorkflowClaim.clickFirstClaimRow,ESSSUPMyWorkflowClaim.logout)
  val supMyWorkflowClaimApprove = exec(ESSSUPMyWorkflowClaim.loadApplication,ESSSUPMyWorkflowClaim.searchClaimApplication,ESSSUPMyWorkflowClaim.clickFirstClaimRow,ESSSUPMyWorkflowClaim.claimApproveForm,ESSSUPMyWorkflowClaim.logout)
  val supMyWorkflowClaimReject = exec(ESSSUPMyWorkflowClaim.loadApplication,ESSSUPMyWorkflowClaim.searchClaimApplication,ESSSUPMyWorkflowClaim.clickFirstClaimRow,ESSSUPMyWorkflowClaim.claimRejectForm,ESSSUPMyWorkflowClaim.logout)

  //Sup Delegation
  val supDelegationSave = exec(ESSSUPMyWorkflowDelegation.loadApplication,ESSSUPMyWorkflowDelegation.openCreateDelegation,ESSSUPMyWorkflowDelegation.clickSaveDelegation,ESSSUPMyWorkflowDelegation.logout)
  val supDelegationReset = exec(ESSSUPMyWorkflowDelegation.loadApplication,ESSSUPMyWorkflowDelegation.openCreateDelegation,ESSSUPMyWorkflowDelegation.logout)
  val supDelegationDelete = exec(ESSSUPMyWorkflowDelegation.loadApplication,ESSSUPMyWorkflowDelegation.deleteDelegation,ESSSUPMyWorkflowDelegation.logout)

  //Sup Mass Approval
  val massLeaveApplication = exec(ESSSUPMyWorkflowMassApprovalLeaveApplication.loadLeaveApplication,ESSSUPMyWorkflowMassApprovalLeaveApplication.approveLeaveApplication,ESSSUPMyWorkflowMassApprovalLeaveApplication.logout)
  val massApprovalLeaveCancellation = exec(ESSSUPMyWorkflowMassApprovalLeaveCancellation.loadMassLeavePage,ESSSUPMyWorkflowMassApprovalLeaveCancellation.clickLeaveCancelation,ESSSUPMyWorkflowMassApprovalLeaveCancellation.approveAllLeaveCancel,ESSSUPMyWorkflowMassApprovalLeaveCancellation.logout)

  //Sup Eprofile
  val supEprofileChangeApprove = exec(ESSSUPMyWorkflowEProfileChanges.loadApplication,ESSSUPMyWorkflowEProfileChanges.searchEProfileType,ESSSUPMyWorkflowEProfileChanges.clickPersonalFirstRow,ESSSUPMyWorkflowEProfileChanges.approvalPersonalForm,ESSSUPMyWorkflowEProfileChanges.logout)
  val supEprofileChangeSave = exec(ESSSUPMyWorkflowEProfileChanges.loadApplication,ESSSUPMyWorkflowEProfileChanges.searchEProfileType,ESSSUPMyWorkflowEProfileChanges.clickPersonalFirstRow,ESSSUPMyWorkflowEProfileChanges.savePersonalForm,ESSSUPMyWorkflowEProfileChanges.logout)
  val supEprofileChangeReject = exec(ESSSUPMyWorkflowEProfileChanges.loadApplication,ESSSUPMyWorkflowEProfileChanges.searchEProfileType,ESSSUPMyWorkflowEProfileChanges.clickPersonalFirstRow,ESSSUPMyWorkflowEProfileChanges.rejectPersonalForm,ESSSUPMyWorkflowEProfileChanges.logout)
  val supEprofileChangeReset = exec(ESSSUPMyWorkflowEProfileChanges.loadApplication,ESSSUPMyWorkflowEProfileChanges.searchEProfileType,ESSSUPMyWorkflowEProfileChanges.clickPersonalFirstRow,ESSSUPMyWorkflowEProfileChanges.logout)

  //SUP
  val supMyTeamViewClaimEntitlement = exec(ESSSUPMyTeamViewClaimEntitlement.LoadClaimEntitlement,ESSSUPMyTeamViewClaimEntitlement.searchClaimEntitlement   ,ESSSUPMyTeamViewClaimEntitlement.logout)
  val supMyTeamViewLeaveEntitlement = exec(ESSSUPMyTeamViewLeaveEntitlement.loadLeaveEntitlement,ESSSUPMyTeamViewLeaveEntitlement.clickLeaveEntitlement   ,ESSSUPMyTeamViewLeaveEntitlement.logout)
  val supMyTeamViewLeaveApplication = exec(ESSSUPMyTeamViewLeaveApplication.loadLeaveApplication,ESSSUPMyTeamViewLeaveApplication.searchLeave,ESSSUPMyTeamViewLeaveApplication.clickRequestId,ESSSUPMyTeamViewLeaveApplication.logout)
  val supMyTeamViewClaimApplication = exec(ESSSUPMyTeamViewClaimApplication.loadViewClaimApplication,ESSSUPMyTeamViewClaimApplication.clickRequestId,ESSSUPMyTeamViewClaimApplication.closeClaimApplication,ESSSUPMyTeamViewClaimApplication.logout)

  //HR profile
  val profileAmendmentPayment = exec(ProfileAmendment.loadEmployeePage,ProfileAmendment.searchFilterAndGetUser,ProfileAmendment.loadEmployeeDetailsPersonalTab,ProfileAmendment.loadEmploymentTab,ProfileAmendment.changePaymentMethod,ProfileAmendment.loadEmploymentTab,ProfileAmendment.logout)
  val profileAmendmentEPFNo = exec(ProfileAmendment.loadEmployeePage,ProfileAmendment.searchFilterAndGetUser,ProfileAmendment.loadEmployeeDetailsPersonalTab,ProfileAmendment.openStatutoryTab,ProfileAmendment.changeEpfNo,ProfileAmendment.loadEmploymentTab,ProfileAmendment.changeSuperiorLeaveGroup,ProfileAmendment.logout)
  val profileAmendmentEPFRate = exec(ProfileAmendment.loadEmployeePage,ProfileAmendment.searchFilterAndGetUser,ProfileAmendment.loadEmployeeDetailsPersonalTab,ProfileAmendment.openStatutoryTab,ProfileAmendment.changeEpfRate,ProfileAmendment.logout)

  val hrEmployeeListingReport = exec(EmployeeListingReport.loadReport,EmployeeListingReport.getReport,EmployeeListingReport.logout)
  val hrEmployeeMovementReport = exec(EmployeeMovementReport.loadMovementReport,EmployeeMovementReport.FilterForm,EmployeeMovementReport.clickSearch,EmployeeMovementReport.logout)
  val hrFixedTransactionListing = exec(FixedTransactionListing.loadFixedTransaction,FixedTransactionListing.selectFilter,FixedTransactionListing.clickSearch,FixedTransactionListing.logout)
  val hrEmployeeLeaveApproverListing = exec(EmployeeLeaveApproverListingReport.loadLeaveApproverListing,EmployeeLeaveApproverListingReport.searchFilter,EmployeeLeaveApproverListingReport.clickSearch,EmployeeLeaveApproverListingReport.logout)
  val hrLeaveApplicationReport = exec(LeaveApplicationReport.loadApplicationReport,LeaveApplicationReport.SearchFilterApplication,LeaveApplicationReport.generateReport,LeaveApplicationReport.logout)
  val hrLeaveEntitlementReport = exec(LeaveEntitlementReport.loadLeaveEntitlementReport,LeaveEntitlementReport.searchProcess,LeaveEntitlementReport.submitSearch,LeaveEntitlementReport.logout)
  val hrClaimApplicationReport = exec(ClaimApplicationReport.LoadClaimApplicationView,ClaimApplicationReport.clickSubmit,ClaimApplicationReport.logout)
  val hrExpensesDetailsforFinance = exec(ExpensesDetailsforFinance.loadExpensesDetails,ExpensesDetailsforFinance.submitForm,ExpensesDetailsforFinance.logout)
  val hrExpensesDetailsforClaim = exec(ExpensesDetailsforClaim.loadExpensesDetails,ExpensesDetailsforClaim.submitForm,ExpensesDetailsforClaim.logout)
  val hrEmployeeLeaveBalanceReport = exec(EmployeeLeaveBalanceReport.loadLeaveBalanceReport,EmployeeLeaveBalanceReport.searchFilter,EmployeeLeaveBalanceReport.generateReport,EmployeeLeaveBalanceReport.logout)
  val hrClaimEntitlementReport = exec(ClaimEntitlementReport.loadClaimEntitlementReport,ClaimEntitlementReport.generateReport,ClaimEntitlementReport.logout)

  //Apply Leave Execution
  val applyLeaveSaveDraftScenario = scenario("Apply Leave Save Draft").exec(login,applyLeaveSaveDraft)
  val applyLeaveDeleteDraftScenario = scenario("Apply Leave Delete Draft").exec(login,applyLeaveDeleteDraft)
  val applyLeaveSubmitScenario = scenario("Apply Leave Submit").exec(login,applyLeaveSubmit)
  val applyLeaveResetScenario = scenario("Apply Leave Reset").exec(login,applyLeaveReset)
  val applyLeaveAttachDraftScenario = scenario("Apply Leave Attach Draft").exec(login,applyLeaveAttachDraft)

  // Cancel Leave Execution
  val CancelLeaveScenario = scenario("Cancel Leave").exec(login,CancelLeave)

  // ESS EMP My Leave Entitlement Execution
  val empMyLeaveEntitlementScenario = scenario("My Leave Entitlement").exec(login,empMyLeaveEntitlement)

  // ESS EMP My Leave Application Execution
  val empMyLeaveApplicatonCheckScenario = scenario ("My Leave Application Check").exec(login,empCheckMyLeaveApplication)
  val empMyLeaveApplicatonWithdrawScenario = scenario ("My Leave Application Withdraw").exec(login,empWithdrawLeaveMyLeaveApplication)

  //ESS EMP Claim
  val empApplyClaimSaveDraftScenario = scenario("Apply Claim Save Draft").exec(loginClaim,empApplyClaimSaveDraft)
  val empApplyClaimDeleteDraftScenario = scenario("Apply Claim Delete Draft").exec(loginClaim,empApplyClaimDeleteDraft)
  val empApplyClaimSubmitScenario = scenario("Apply Claim Submit Draft").exec(loginClaim,empApplyClaimSubmit)
  val empApplyClaimResetScenario = scenario("Apply Claim Reset Draft").exec(loginClaim,empApplyClaimReset)
  val empApplyClaimAttachScenario = scenario("Apply Claim Attach Draft").exec(loginClaim,empApplyClaimAttach)

  //Claim
  val empMyClaimEntitlementScenario = scenario("My Claim entitlement").exec(loginClaim,empMyClaimEntitlement)
  val checkClaimApplicationScenario = scenario("Check Claim Application").exec(loginClaim,checkClaimApplicationex)
  val withdrawClaimApplicationScenario = scenario("Withdraw Claim Application").exec(loginClaim,withdrawClaimApplicationex)

  //Profile
  val profileResetScenario = scenario("My profile reset").exec(login,myProfileReset)
  val myProfileSaveDraftScenario = scenario("My profile saveDraft").exec(login,myProfileSaveDraft)
  val myProfileSubmitDraftScenario = scenario("My profile submitDraft").exec(login,myProfileSubmitDraft)
  val myProfileSubmitScenario = scenario("My profile Submit").exec(login,myProfileSubmit)
  val myProfileDeleteDraftScenario = scenario("My profile delete Draft").exec(login,myProfileDeleteDraft)

  //Document
  val documentCreateScenario = scenario("My Document Create").exec(login,myDocumentCreateex)
  val documentDownloadScenario = scenario("My Document Download").exec(login,myDocumentDownloadex)
  val documentDeleteScenario = scenario("My Document Delete").exec(login,myDocumentDeleteex)

  //Service History
  val serviceHistroyScenario = scenario("My Service History").exec(login,MyServiceHistoryex)

  //emp Workflow Leave
  val empWorkFlowViewLeaveScenario = scenario("Worklow View Leave").exec(login,empWorkFlowViewLeave)
  val empWorkFlowWithdrawScenario = scenario("Worklow Withdraw Leave").exec(login,empWorkFlowWithdrawLeave)
  val empWorkFlowWithdrawCancelLeaveScenario = scenario("Worklow Withdraw Cancel Leave").exec(login,empWorkFlowWithdrawCancelLeave)

  //emp Workflow View Claim
  val empMyWorkflowViewClaimScenario = scenario("Workflow View Claim").exec(loginClaim,empMyWorkflowViewClaim)
  val empMyWorkflowWithdrawClaimScenario = scenario("Workflow Withdraw Claim").exec(loginClaim,empMyWorkflowWithdrawClaim)

  //sup WorkFlow Leave
  val supWorkflowLeaveSaveScenario = scenario("Workflow Leave Save").exec(loginSup,supMyWorkflowLeaveSave)
  val supWorkflowLeaveResetScenario = scenario("Workflow Leave Reset").exec(loginSup,supMyWorkflowLeaveReset)
  val supWorkflowLeaveRejectScenario = scenario("Workflow Leave Reject").exec(loginSup,supMyWorkflowLeaveReject)
  val supWorkflowLeaveApproveScenario = scenario("Workflow Approve Reject").exec(loginSup,supMyWorkflowLeaveApprove)

  //sup workflow Claim
  val supWorkflowClaimSaveScenario = scenario("Workflow Leave Save").exec(loginSup,supMyWorkflowClaimSave)
  val supWorkflowClaimResetScenario = scenario("Workflow Leave Reject").exec(loginSup,supMyWorkflowClaimReset)
  val supWorkflowClaimApproveScenario = scenario("Workflow Leave Approve").exec(loginSup,supMyWorkflowClaimApprove)
  val supWorkflowClaimRejectScenario = scenario("Workflow Leave Reject").exec(loginSup,supMyWorkflowClaimReject)

  //sup Delegation
  val supDelegationSaveScenario = scenario("Deletegation Save").exec(loginSup,supDelegationSave)
  val supDelegationResetScenario = scenario("Deletegation Reset").exec(loginSup,supDelegationReset)
  val supDelegationDeleteScenario = scenario("Deletegation Delete").exec(loginSup,supDelegationDelete)

  //sup Mass Approval
  val massLeaveApplicationScenario = scenario("Mass Leave Application").exec(loginSup,massLeaveApplication)
  val massApprovalLeaveCancellationScenario = scenario("Mass Leave Application").exec(loginSup,massApprovalLeaveCancellation)

  //sup E profile
  val supEprofileChangeApproveScenario =  scenario("E profile Approve").exec(loginSup,supEprofileChangeApprove)
  val supEprofileChangeSaveScenario =  scenario("E profile Save").exec(loginSup,supEprofileChangeSave)
  val supEprofileChangeRejectScenario =  scenario("E profile Reject").exec(loginSup,supEprofileChangeReject)
  val supEprofileChangeResetScenario =  scenario("E profile Reset").exec(loginSup,supEprofileChangeReset)

  //SUP
  val MyTeamViewClaimEntitlementScenario = scenario("My Team View Claim Entitlement").exec(loginSup,supMyTeamViewClaimEntitlement)
  val MyTeamViewLeaveEntitlementScenario = scenario("My Team View Leave Entitlement").exec(loginSup,supMyTeamViewLeaveEntitlement)
  val MyTeamViewLeaveApplicationScenario = scenario("My Team View Leave Application").exec(loginSup,supMyTeamViewLeaveApplication)
  val MyTeamViewClaimApplicationScenario = scenario("My Team View Claim Application").exec(loginSup,supMyTeamViewClaimApplication)

  //HR
  val profileAmendmentPaymentScenario = scenario("Amend Payment Method").exec(loginHr,profileAmendmentPayment)
  val profileAmendmentEPFRateScenario = scenario("Amend EPF Rate").exec(loginHr,profileAmendmentEPFRate)
  val profileAmendmentEPFNoScenario = scenario("Amend EPF NO,Leave Group,Superior").exec(loginHr,profileAmendmentEPFNo)

  //HR Profile
  val ProfileEmployeeListingReportS = scenario("Profile Employee Listing Report").exec(loginHr,hrEmployeeListingReport)
  val ProfileEmployeeMovementReportS = scenario("Profile Employee Movement Report").exec(loginHr,hrEmployeeMovementReport)
  val ProfileFixedTransactionListingS = scenario("Profile Fixed Transaction Listing Report").exec(loginHr,hrFixedTransactionListing)
  val LeaveEmployeeApproverListingS = scenario("Leave Employee Approver Listing Report").exec(loginHr,hrEmployeeLeaveApproverListing)
  val LeaveApplicationReportListingS = scenario("Leave Leave Application Report").exec(loginHr,hrLeaveApplicationReport)
  val LeaveEntitlementReportListingS = scenario("Leave Leave Entitlement Report").exec(loginHr,hrLeaveEntitlementReport)
  val ClaimApplicationReportListingS = scenario("Claim Application Report").exec(loginHr,hrClaimApplicationReport)
  val ExpensesDetailsforFinanceListingS = scenario("Expenses Details For Finance").exec(loginHr,hrExpensesDetailsforFinance)
  val ExpensesDetailsforClaimListingS = scenario("Expenses Details For Claim").exec(loginHr,hrExpensesDetailsforClaim)
  val EmployeeLeaveBalanceReportS = scenario("Employee Leave Balance Report").exec(loginHr,hrEmployeeLeaveBalanceReport)
  val ClaimEntitlementReportS = scenario("Claim entitlement Report").exec(loginHr,hrClaimEntitlementReport)

 // setUp(profileAmendmentEPFNoScenario.inject(atOnceUsers(1))).protocols(httpProtocol)

  setUp(applyLeaveSaveDraftScenario.inject(rampUsers(EMPNo).during(scriptDuration.minutes))

      .andThen(supWorkflowLeaveSaveScenario.inject(rampUsers(HRNo).during(scriptDuration.minutes)))
  ).protocols(httpProtocol)




}
