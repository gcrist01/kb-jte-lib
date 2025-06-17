void call() {
    
    stage('Compile') {
        environment {
          // Jenkins user hacks
          DOTNET_CLI_HOME = "${env.WORKSPACE}/.dotnet"
          DOCKER_CONFIG="/home/jenkins/.docker"
          PATH = "${env.WORKSPACE}/.dotnet/tools:${env.PATH}"
          DOTNET_CLI_TELEMETRY_OPTOUT = 1
          HOME="/home/jenkins/"
          // Hacks to make it work as the CICD environment would
          PROD_ECR_HOST_NAME="250300400957.dkr.ecr.ap-southeast-2.amazonaws.com"
          NON_PROD_ECR_HOST_NAME="041371538652.dkr.ecr.ap-southeast-2.amazonaws.com"
          SERVICEACCOUNT_NAME="sf-thing-api"
          STAGE_ACCOUNT_ID="041371538652"
          STAGE_ECR_HOST_NAME="041371538652.dkr.ecr.ap-southeast-2.amazonaws.com"

        }
        node ('cake') {
            environment {
                KB_CODEBUILD_SRC_DIR="${env.WORKSPACE}"
                KB_STAGE_NAME="Build"
            }

            echo "config Type is: ${config.getClass().name}"
            stage('Checkout') {
                checkout scm
            }

            stage('Build') {
                def scriptPath = config.KB_SCRIPT_PATH
                def cakeScript = sh(script: "yq '.config.build.cakeScript' ${env.WORKSPACE}/${scriptPath}/pipeline.yaml", returnStdout: true).trim()
                echo "BUILD_CAKE_SCRIPT config is ${cakeScript}"
                if (cakeScript) {
                    echo "cakeScript configured"
                }
                else{
                    // use Template default, yeah I know
                    cakeScript = "/home/jenkins/template-run/cake/build.min.cake"
                }
                def projectPath = config.KB_PROJECT_PATH
                echo "Use Cake Build on ${projectPath}..."
                dir("${projectPath}"){
                    echo "Calling cake ${cakeScript}"
                    sh 'whoami && id'
                    sh "echo home $HOME"
                    echo "KB_CODEBUILD_SRC_DIR ${env.KB_CODEBUILD_SRC_DIR}"

                    def envVars = [
                    "KB_SCRIPT_PATH=${scriptPath}",
                    "KB_CODEBUILD_SRC_DIR=${env.WORKSPACE}",
                    "HOME=${env.WORKSPACE}"
                    "PROD_ECR_HOST_NAME=250300400957.dkr.ecr.ap-southeast-2.amazonaws.com"
                    "NON_PROD_ECR_HOST_NAME=041371538652.dkr.ecr.ap-southeast-2.amazonaws.com"
                    "SERVICEACCOUNT_NAME=sf-thing-api"
                    "STAGE_ACCOUNT_ID=041371538652"
                    "STAGE_ECR_HOST_NAME=041371538652.dkr.ecr.ap-southeast-2.amazonaws.com"
                    ]

                    sh "HOME=$WORKSPACE dotnet new tool-manifest --force"
                    sh "HOME=$WORKSPACE dotnet tool restore"
                    sh "HOME=$WORKSPACE dotnet cake --info"
                    withEnv(envVars) {
                        //sh "KB_SCRIPT_PATH=${scriptPath} KB_CODEBUILD_SRC_DIR=${env.WORKSPACE} HOME=$WORKSPACE dotnet cake ${cakeScript} --nugetconfig /home/jenkins/.nuget/NuGet/NuGet.Config --verbosity Verbose"
                        sh "dotnet cake ${cakeScript} --nugetconfig /home/jenkins/.nuget/NuGet/NuGet.Config --verbosity Verbose"
                    }                        
                }
            }
        }
    }
}
