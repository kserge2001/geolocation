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
                withSonarQubeEnv('Sonarqube-9.3')
                sh 'mvn sonar:sonar'
                }
            }   
            
        }
        stage('Quality Gate'){
            steps{
                    timeout(time: 20, unit: 'MINUTES') {
                          def qg = waitForQualityGate()
                           if (qg.status != 'OK') {
                            error "Pipeline stopped because of quality gate status: ${qg.status}"
                           }
                    }

                }
            }
        }
    

    

