pipeline{

    agent any
    tiils {
        maven "M2_HOME"
    }


    stages{
        stage('maven'){
            steps{
                sh mvn clean install package
            }
        }
    }
}