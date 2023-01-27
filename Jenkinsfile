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
        stage('UNIT Testing'){
            steps{
               sh 'mvn test'
               
            }
        }
        stage('Integration Testing'){
            steps{
               sh 'mvn verify -DskipUnitTests'  
            }
        }
        stage('Maven Build'){
            steps {
                sh 'mvn clean install package'
            }
        }
        stage('SonarQube Analysis'){
            
            steps {
                script { 
                        withSonarQubeEnv {
                         sh 'mvn clean package sonar:sonar'
                        }
                    }
                  
            }
        }
        stage('Quality-Gate'){
            steps {
                script{
                    timeout(time: 1, unit: 'HOURS') {
                         waitForQualityGate false
                    }
                }
            }   
        }
        
        
    }
}