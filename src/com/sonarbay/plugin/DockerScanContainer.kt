package com.sonarbay.plugin

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement

/**
 * Created by sh4d0wz on 8/28/17.
 */
class DockerScanContainer (element: PsiElement): Runnable {

    override fun run() {
        println("thread dispatched...")
    }
}