def mods

import org.jenkinsci.plugins.pipeline.modeldefinition.when.impl.ChangeSetConditional

def hasChanges(String pattern) {
    def changeLogSets = currentBuild.changeSets
    def conditional = new ChangeSetConditional(pattern)

    for (set in changeLogSets) {
        def entries = set.items
        for (entry in entries) {
            if (conditional.changeSetMatches(entry, pattern, false)) {
                return true;
            }
        }
    }

    return false;
}

pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                scmSkip(deleteBuild: true, skipPattern:'.*\\[ci skip\\].*')
            }
        }

        stage('Create Mod List') {
            steps {
                script {
                    // you may create your list here, lets say reading from a file after checkout
                    mods = ["nompmenu","update-me"]
                }
            }
        }

        stage('Mods') {
            steps {
                script {
                    for (int i = 0; i < mods.size(); i++) {
                        stage(mods[i]) {
                            if(hasChanges("${mods[i]}/**")) {
                                withGradle {
                                    sh "./gradlew :${mods[i]}:remapJar"
                                }
                                archiveArtifacts artifacts: '${mods[i]}/build/libs/*.jar', fingerprint: true, followSymlinks: false, onlyIfSuccessful: true
                            }
                        }
                    }
                }
            }
        }
    }
}