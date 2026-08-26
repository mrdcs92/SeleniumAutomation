pipeline {
    agent any

	parameters {
		
		choice(
			name: 'TEST_SUITE',
			choices: [
				'Regression',
				'Purchase',
				'ErrorValidation',
				'Cucumber',
				'DbTest'
			],
			description: 'Select the Maven test profile to execute'
		)
		
	}
	
    stages {

        stage('Verify Environment') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Run Regression Tests') {
            steps {
                bat "mvn clean test -P${params.TEST_SUITE}"
            }
        }
    }

    post {
        success {
            echo "Test suite ${params.TEST_SUITE} passed."
        }

        failure {
            echo "Test suite ${params.TEST_SUITE} failed."
        }

        always {
            echo "Pipeline execution finished."
        }
    }
}