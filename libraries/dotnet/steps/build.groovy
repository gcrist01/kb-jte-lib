void call() {
    stage('Build') {
        println 'dotnet build'
    }
    stage('Test') {
        println 'dotnet test'
    }
}