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
   

    stages {
        stage("build & SonarQube analysis") {
            
            steps {
              withSonarQubeEnv('SonarServer') {
                  sh 'mvn sonar:sonar -Dsonar.projectKey=kserge2001_geolocation -Dsonar.java.binaries=.'
              }
            }
          }
        stage("Quality gate") {
            steps {
                waitForQualityGate abortPipeline: true
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
