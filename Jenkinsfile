node {
	parameters {
        string(name: 'Greeting', defaultValue: 'Hello', description: 'How should I greet the world?')
    }
	properties([
		[$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', numToKeepStr: '15']]
	])
	
	stage('Checkout') {
		checkout scm
		
		dir('build') { deleteDir() }
		dir('.m2/repository/org/eclipse/xtext') { deleteDir() }
		dir('.m2/repository/org/eclipse/xtend') { deleteDir() }
	}
	
	stage('Maven Build') {
		def mvnHome = tool 'M3'
		def targetProfile = ""
		if ("oxygen" == params.target_platform) {
			targetProfile = "-PuseOxygenTarget"
		} else if ("photon" == params.target_platform) {
			targetProfile = "-PusePhotonTarget"
		}
		echo "xxxxxx ${targetProfile}"
	}
	
	archive 'build/**'
}
