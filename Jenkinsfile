pipeline{
    agen any
    tools {
        maven 'M2_HOME'
    }
    stages{
        stage('mavin build'){
            steps{
                sh mvn clean install package
            }

        }
    }
}