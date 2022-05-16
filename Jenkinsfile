pipeline {
    triggers {
  pollSCM('* * * * *')
    }
    agent any
    tools {
  maven 'M2_HOME'
}
   

    stages {
        stage('maven package') {
            steps {
                sh 'mvn clean'
                sh 'mvn install'
                sh 'mvn package'
            }
        }
          stage('test') {
            steps {
               sh 'mvn test'
                
            }
        }
        stage("build & SonarQube analysis") {
            agent any
            steps {
              withSonarQubeEnv('sonarQube') {
                sh 'mvn sonar:sonar'
              }
            }
          }
          stage("Quality Gate") {
            steps {
              timeout(time: 1, unit: 'HOURS') {
                waitForQualityGate abortPipeline: true
              }
            }
          }
         
          stage('deploy') {
            steps {
                echo 'deployement'
                
            }
        }
    }
}
