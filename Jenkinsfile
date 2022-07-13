pipeline{

    agent any
    tools {
        maven "M2_HOME"
    }


    stages{
        stage('maven build'){
            steps{
                sh 'mvn clean install package'
            }
        }
    }

    stages{
        stage('ckeck pwd'){
            steps{
                sh 'pwd'
            }
        }
    }


    stages{
        stage('list the dir'){
            steps{
                sh 'ls'
            }
        }
    }
}