package com.sonarbay.plugin

import com.intellij.openapi.fileTypes.ExactFileNameMatcher

/**
 * Created by sh4d0wz on 8/23/17.
 */
class DockerTypeFactory : com.intellij.openapi.fileTypes.FileTypeFactory() {

    override fun createFileTypes(fileTypeConsumer: com.intellij.openapi.fileTypes.FileTypeConsumer) {
        // usual file name
        fileTypeConsumer.consume(DockerFiletype.Companion.INSTANCE, ExactFileNameMatcher("Dockerfile"))

        // just in case someone decides to get crazy :-)
        fileTypeConsumer.consume(DockerFiletype.Companion.INSTANCE, ExactFileNameMatcher("DOCKERFILE"))
    }
}