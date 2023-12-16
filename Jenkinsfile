pipeline {
    agent any
    tools {
        maven 'M2_HOME'
    }
    stages {
        stage("maven build") {
            steps{
                sh 'mvn clean install package'
            }

        }
        stage("upload artifact") {
            steps{
                script{
             nexusArtifactUploader artifacts: 
              [[artifactId:  "${POM_ARTIFACTID}", 
                classifier: '', 
                  file: "target/${POM_ARTIFACTID}-${POM_VERSION}.${POM_PACKAGING}", 
                   type: "${POM_PACKAGING}"]], 
                    credentialsId: 'Nexusid', 
                     groupId:  "${POM_GROUPID}"", 
                      nexusUrl: '69.164.206.106:8081', 
                       nexusVersion: 'nexus3', 
                        protocol: 'http', 
                         repository: 'Biomedical', 
                          version: "${POM_VERSION}"
                }          
            }

        }
        stage("list the dir") {
            steps{
                sh 'ls'
            }

        }
    }
}