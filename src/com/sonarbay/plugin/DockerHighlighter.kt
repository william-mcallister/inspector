package com.sonarbay.plugin

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.sonarbay.plugin.DockerAdapter
import com.sonarbay.plugin.psi.DockerTypes

/**
 * Created by sh4d0wz on 8/24/17.
 */
class DockerHighlighter : SyntaxHighlighterBase() {
    companion object {
        val SEPARATOR : TextAttributesKey = TextAttributesKey.createTextAttributesKey("DOCKER_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)
        val KEY : TextAttributesKey = TextAttributesKey.createTextAttributesKey("DOCKER_KEY", DefaultLanguageHighlighterColors.KEYWORD)
        val VALUE : TextAttributesKey = TextAttributesKey.createTextAttributesKey("DOCKER_VALUE", DefaultLanguageHighlighterColors.STRING)
        val COMMENT : TextAttributesKey = TextAttributesKey.createTextAttributesKey("DOCKER_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val BAD_CHARACTER : TextAttributesKey = TextAttributesKey.createTextAttributesKey("DOCKER_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

        private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
        private val SEPARATOR_KEYS = arrayOf(SEPARATOR)
        private val KEY_KEYS = arrayOf(KEY)
        private val VALUE_KEYS = arrayOf(VALUE)
        private val COMMENT_KEYS = arrayOf(COMMENT)
        private val EMPTY_KEYS = arrayOf(TextAttributesKey.createTextAttributesKey(""))
    }

    override fun getHighlightingLexer(): Lexer {
        return DockerAdapter()
    }

    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> {
        if (tokenType!!.equals(DockerTypes.SEPARATOR)) {
            return SEPARATOR_KEYS
        } else if (tokenType.equals(DockerTypes.KEY)) {
            return KEY_KEYS
        } else if (tokenType.equals(DockerTypes.VALUE)) {
            return VALUE_KEYS
        } else if (tokenType.equals(DockerTypes.COMMENT)) {
            return COMMENT_KEYS
        } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS
        } else {
            return EMPTY_KEYS
        }

    }
}