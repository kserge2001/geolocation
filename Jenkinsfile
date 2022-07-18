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
        stage("Quality Gate"){

        timeout(time: 10, unit: ‘MINUTES’) {
              def qg= waitForQualityGate()
            if (qg.status!= ‘OK’){
                error “Pipeline aborted due to quality gate failure: ${qg.status}”
            }
        }         
              echo ‘Quality Gate Passed’

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
