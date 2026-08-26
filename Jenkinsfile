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
		
		choice(
			name: 'BROWSER',
			choices: [
				'chrome',
				'edge',
				'firefox',
			],
			description: 'Browser to use for Selenium'
		)
		
		choice(
			name: 'HEADLESS',
			choices: [
				' ',
				'headless'
			],
			description: 'Choice of running headless or not'
		)
		
	}
	
    stages {

        stage('Verify Environment') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Run -P${params.TEST_SUITE} Tests') {
            steps {
                bat "mvn clean test -P${params.TEST_SUITE} -Dbrowser=${params.BROWSER}${params.HEADLESS}"
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