void call() {
    
    stage('Build') {
        environment {
          // Jenkins user hacks
          DOTNET_CLI_HOME = "${env.WORKSPACE}/.dotnet"
          DOCKER_CONFIG="/home/jenkins/.docker"
          PATH = "${env.WORKSPACE}/.dotnet/tools:${env.PATH}"
          DOTNET_CLI_TELEMETRY_OPTOUT = 1
          // Hacks to make it work as the CICD environment would
          PROD_ECR_HOST_NAME="250300400957.dkr.ecr.ap-southeast-2.amazonaws.com"
          NON_PROD_ECR_HOST_NAME="041371538652.dkr.ecr.ap-southeast-2.amazonaws.com"
          SERVICEACCOUNT_NAME="sf-thing-api"
          STAGE_ACCOUNT_ID="041371538652"
          STAGE_ECR_HOST_NAME="041371538652.dkr.ecr.ap-southeast-2.amazonaws.com"
          KB_CODEBUILD_SRC_DIR="${env.WORKSPACE}"
          KB_STAGE_NAME="Build"
        }
        
        echo "config Type is: ${config.getClass().name}"
        agent {
            label 'cake'

            dir("MinimalWebApp2") {
                echo "config Type is: ${config.getClass().name}"
            }
        }
    }
}

//
//        //println config.getClass().name
//        echo "config Type is: ${config.getClass().name}"
//        echo "stepContext Type is: ${stepContext.getClass().name}"        
//        println "KB_SCRIPT_PATH -> ${config.KB_SCRIPT_PATH}" 
//        def scriptPath = config.KB_SCRIPT_PATH
//        def projectPath = config.KB_PROJECT_PATH
//
//        environment {
//            // Jenkins user hacks
//            DOTNET_CLI_HOME = "${env.WORKSPACE}/.dotnet"
//            DOCKER_CONFIG="/home/jenkins/.docker"
//            PATH = "${env.WORKSPACE}/.dotnet/tools:${env.PATH}"
//            DOTNET_CLI_TELEMETRY_OPTOUT = 1
//
//            // Hacks to make it work as the CICD environment would
//            PROD_ECR_HOST_NAME="250300400957.dkr.ecr.ap-southeast-2.amazonaws.com"
//            NON_PROD_ECR_HOST_NAME="041371538652.dkr.ecr.ap-southeast-2.amazonaws.com"
//            SERVICEACCOUNT_NAME="sf-thing-api"
//            STAGE_ACCOUNT_ID="041371538652"
//            STAGE_ECR_HOST_NAME="041371538652.dkr.ecr.ap-southeast-2.amazonaws.com"
//
//            KB_CODEBUILD_SRC_DIR="${env.WORKSPACE}"
//            KB_STAGE_NAME="Build"
//        }
//        steps {
//            echo "Use Cake Build on ${projectPath}..."
//            //dir("${projectPath}") {
//            //    script {
//            //        def cakeScript = sh(script: "yq '.config.build.cakeScript' ${KB_CODEBUILD_SRC_DIR}/${scriptPath}/pipeline.yaml", returnStdout: true).trim()
//            //        echo "BUILD_CAKE_SCRIPT config is ${cakeScript}"
//            //        if (cakeScript) {
//            //            echo "cakeScript configured"
//            //        }
//            //        else{
//            //            // use Template default, yeah I know
//            //            cakeScript = "/home/jenkins/template-run/cake/build.min.cake"
//            //        }
//            //        echo "Calling cake ${cakeScript}"
//            //        sh "dotnet cake ${cakeScript} --nugetconfig ${HOME}/.nuget/NuGet/NuGet.Config --verbosity Verbose"
//            //    }
//            //}
//        }
//    }
//}