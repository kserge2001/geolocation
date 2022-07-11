pipeline {
    triggers {
  pollSCM('* * * * *')
    }
   agent {
        docker { image 'maven:3.8.6-openjdk-18' }
   }
    tools {
  maven 'maven3'
}
   

    stages {
        stage("build & SonarQube analysis") {
            
            steps {
              withSonarQubeEnv('sonar') {
                  sh 'mvn sonar:sonar'
              }
            }
          }
        
          stage('Sonar Quality Gate') {
        sleep 180
        timeout(time: 25, unit: 'MINUTES') {
            def qg = waitForQualityGate()
                    if (!(qg.status == 'OK' || qg.status == 'WARN')) {
                        error "Pipeline aborted due to quality gate failure: ${qg.status}"
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
                echo 'deployement'
                
            }
        }
    }
}
