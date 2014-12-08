import skinny.task._, generator._
object TaskRunner extends SkinnyTaskLauncher {

  lazy val modelGenerator = new ModelGenerator {
    override def useAutoConstruct = true
    override def sourceDir = "app"
    override def testSourceDir = "test"
    override def modelPackage = "models"
  }

  register("generate:model", (params) => {
    modelGenerator.run(params)
  })
}

