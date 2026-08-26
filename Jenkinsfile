pipeline {
    agent any

    stages {

        stage('Verify Environment') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Run Regression Tests') {
            steps {
                bat 'mvn clean test -PRegression'
            }
        }
    }

    post {
        success {
            echo 'Automation pipeline completed successfully.'
        }

        failure {
            echo 'Automation pipeline failed.'
        }

        always {
            echo 'Pipeline execution finished.'
        }
    }
}