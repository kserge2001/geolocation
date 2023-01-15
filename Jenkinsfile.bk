pipeline {
   agent any
    tools {
        maven 'M2_HOME'
   }
   stages{
    stage ('maven build'){
        steps{
            sh 'mvn clean install package'
            }
        }
        stage ('upload artifact'){
             steps{
                script {
                    def mavenPom = readMavenPom file: 'pom.xml'
            nexusArtifactUploader artifacts:
             [[artifactId: "${mavenPom.artifactId}", 
                classifier: '', 
                  file: "target/${mavenPom.artifactId}-${mavenPom.version}.${mavenPom.packaging}", 
                    type: "${mavenPom.packaging}"]], 
                       credentialsId: "NexusID", 
                          groupId: "${mavenPom.groupId}", 
                            nexusUrl: '192.168.78.112:8081', 
                              nexusVersion: 'nexus3', 
                                protocol: 'http', 
                                  repository: 'maven-nexus-repo',
                                    version: "${mavenPom.version}"
                }
            }
       }
       stage("build & SonarQube analysis") {
            agent {
        docker { image 'maven:3.8.6-openjdk-11-slim' }
            }
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
                            error "Pipeline stopped because of quality gate status: ${qg.status}"
                        }
                    }
                }
            }
        }
    }
}
