
pipeline {
    triggers {
  pollSCM('* * * * *')
    }
   agent {
        docker { image 'maven:3.8.6-openjdk-11-slim' }
   }
    tools {
  maven 'M2_HOME'
}
    script {
    def mavenPom = readMavenPom file: 'pom.xml'
    }
   

    stages {

    
        stage("print variables"){
            steps{
            echo "My variable is ${mavenPom.version}"
            }
        }
  

        stage("build & SonarQube analysis") {
            
            steps {
              withSonarQubeEnv('SonarServer') {
                  sh 'mvn sonar:sonar -Dsonar.projectKey=kserge2001_geolocation -Dsonar.java.binaries=.'
              }
            }
          }
        stage('Check Quality Gate') {
            steps {
                echo 'Checking quality gate...'
                script {
                    timeout(time: 20, unit: 'MINUTES') {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                }
            }
        }
        
         
        stage('maven package') {
            steps {
                sh 'mvn clean'
                sh 'mvn install -DskipTests'
                sh 'mvn package -DskipTests'
            }
        }
          
         
          stage('deploy') {
            steps {
                echo 'deployement'
                
            }
        }
    }
}
