void call() {
    stage('Compile') {
        println 'legacy compile'
    }
    stage('Test') {
        println 'legacy test'
    }
}