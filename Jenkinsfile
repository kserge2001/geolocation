pipeline {
    triggers {
  pollSCM('* * * * *')
    }
    agent any
    tools {
  maven 'M2_HOME'
}
   

    stages {
        stage("build & SonarQube analysis") {
            agent any
            steps {
              withSonarQubeEnv('sonar') {
                sh 'mvn verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=kserge2001_geolocation'
              }
            }
          }
         stage('maven analys') {
            steps {
                sh 'mvn checkstyle:checkstyle'
            }
        }
          stage("Quality Gate") {
            steps {
              timeout(time: 1, unit: 'HOURS') {
                waitForQualityGate abortPipeline: true
              }
            }
          }
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
        
         
          stage('deploy') {
            steps {
                echo 'deploy'
                
            }
        }
    }
}
