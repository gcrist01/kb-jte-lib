void call() {
    stage('Build') {
        println 'legacy build'
    }
    stage('Test') {
        println 'legacy test'
    }
}