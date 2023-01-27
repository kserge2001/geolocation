pipeline {
    agent any
    tools {
        maven 'M2_HOME'
    }
    stages{
        stage('Git Checkout'){
            steps{
                git branch: 'main', url: 'https://github.com/henrykrop2022/geolocation-23.git'
            }
        }
        stage('UNIT Testing') {
            steps{
                sh 'mvn test'
            }
        }
        stage('Integration Testing') {
            steps{
                script{
                    sh 'mvn verify -DskipUnitTests'
                }
            }
        }
        stage('Maven Build'){
            steps{
                sh 'mvn clean install package'
            }
        }
        stage(' SonarQube Analysis'){
            steps {
                withSonarQubeEnv(credentialsId: 'sonarqube-token') {
                sh 'mvn sonar:sonar'
                }
            }   
            
        }
        stage('Quality Gate'){
            steps{
                script {
                    timeout(time: 20, unit: 'MINUTES'){
                          def qg = waitForQualityGate()
                           if (qg.status != 'OK') {
                            error "Pipeline stopped because of quality gate status: ${qg.status}"
                           }
                    }

                }
            }
        }
    }
}

    

