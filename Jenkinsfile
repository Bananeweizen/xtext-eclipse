node {
	properties([
		[$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', numToKeepStr: '15']],
		parameters([
			choice(choices: 'luna,oxygen,photon', description: 'What AWS region?', name: 'target_platform')
  
   		])
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
