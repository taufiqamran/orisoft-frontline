import java.nio.file.{Path, Paths}


object IDEPathHelper {

//  val gatlingConfUrl: Path = getClass.getClassLoader.getResource("gatling.conf")
//  val projectRootDir = gatlingConfUrl.ancestor(3)
//
//  val mavenSourcesDirectory = projectRootDir / "src" / "test" / "scala"
//  val mavenResourcesDirectory = projectRootDir / "src" / "test" / "resources"
//  val mavenTargetDirectory = projectRootDir / "target"
//  val mavenBinariesDirectory = mavenTargetDirectory / "test-classes"

//  val resourcesDirectory = mavenResourcesDirectory
//  val recorderSimulationsDirectory = mavenSourcesDirectory
//  val resultsDirectory = mavenTargetDirectory / "gatling"
//  val recorderConfigFile = mavenResourcesDirectory / "recorder.conf"
  private val projectRootDir = Paths.get(getClass.getClassLoader.getResource("gatling.conf").toURI).getParent.getParent.getParent
  private val mavenTargetDirectory = projectRootDir.resolve("target")
  private val mavenSrcTestDirectory = projectRootDir.resolve("src").resolve("test")

  val mavenSourcesDirectory = mavenSrcTestDirectory.resolve("scala")
  val mavenResourcesDirectory = mavenSrcTestDirectory.resolve("resources")
  val mavenBinariesDirectory = mavenTargetDirectory.resolve("test-classes")
  val resultsDirectory = mavenTargetDirectory.resolve("gatling")
  val recorderConfigFile = mavenResourcesDirectory.resolve("recorder.conf")

}
