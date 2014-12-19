import skinny.task._, generator._

object TaskRunner extends SkinnyTaskLauncher {

  register("model", (params) => PlayModelGenerator.run(params))
  register("reverse-model", (params) => PlayReverseModelGenerator.run(params))
  register("reverse-model-all", (params) => PlayReverseModelAllGenerator.run(params))
}

