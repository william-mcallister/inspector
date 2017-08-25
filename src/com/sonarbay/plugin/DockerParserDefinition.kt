package com.sonarbay.plugin

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import com.sonarbay.plugin.parser.DockerParser
import com.sonarbay.plugin.psi.DockerTypes
import com.sonarbay.plugin.psi.Dockerfile

/**
 * Created by sh4d0wz on 8/23/17.
 */
class DockerParserDefinition : ParserDefinition {

    companion object {
        val WHITE_SPACES : TokenSet = TokenSet.create(TokenType.WHITE_SPACE)
        val FILE : IFileElementType = IFileElementType(DockerLanguage.INSTANCE)
    }

    override fun createLexer(p0: Project?): Lexer {
        return DockerAdapter()
    }

    override fun getWhitespaceTokens(): TokenSet {
        return WHITE_SPACES
    }

    override fun getStringLiteralElements(): TokenSet {
        return TokenSet.EMPTY
    }

    override fun createParser(p0: Project?): PsiParser {
        return DockerParser()
    }

    override fun createFile(fileViewProvider: FileViewProvider): PsiFile {
        return Dockerfile(fileViewProvider)
    }

    override fun spaceExistanceTypeBetweenTokens(p0: ASTNode?, p1: ASTNode?): ParserDefinition.SpaceRequirements {
        return ParserDefinition.SpaceRequirements.MAY
    }

    override fun getFileNodeType(): IFileElementType {
        return FILE
    }

    override fun createElement(node: ASTNode?): PsiElement {
        return DockerTypes.Factory.createElement(node)
    }

    override fun getCommentTokens(): TokenSet {
        return TokenSet.EMPTY
    }
}