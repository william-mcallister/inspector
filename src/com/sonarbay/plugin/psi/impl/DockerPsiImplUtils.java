package com.sonarbay.plugin.psi.impl;

/**
 * Created by sh4d0wz on 8/24/17.
 */
        import com.intellij.lang.ASTNode;
        import com.intellij.navigation.ItemPresentation;
        import com.intellij.psi.*;

        import com.sonarbay.plugin.DockerIcon;
        import com.sonarbay.plugin.psi.DockerElementFactory;
        import com.sonarbay.plugin.psi.DockerTypes;
        import com.sonarbay.plugin.psi.DockerfileProperty;
        import org.jetbrains.annotations.Nullable;

        import javax.swing.*;

public class DockerPsiImplUtils {
    public static String getKey(DockerfileProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(DockerTypes.KEY);
        if (keyNode != null) {
            // IMPORTANT: Convert embedded escaped spaces to simple spaces
            return keyNode.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }

    public static String getValue(DockerfileProperty element) {
        ASTNode valueNode = element.getNode().findChildByType(DockerTypes.VALUE);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return null;
        }
    }

    public static String getName(DockerfileProperty element) {
        return getKey(element);
    }

    public static PsiElement setName(DockerfileProperty element, String newName) {
        ASTNode keyNode = element.getNode().findChildByType(DockerTypes.KEY);
        if (keyNode != null) {
            DockerfileProperty property = DockerElementFactory.Companion.createProperty(element.getProject(), newName);
            ASTNode newKeyNode = property.getFirstChild().getNode();
            element.getNode().replaceChild(keyNode, newKeyNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(DockerfileProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(DockerTypes.KEY);
        if (keyNode != null) {
            return keyNode.getPsi();
        } else {
            return null;
        }
    }

    public static ItemPresentation getPresentation(final DockerfileProperty element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return "";
            }

            @Nullable
            @Override
            public String getLocationString() {
                PsiFile containingFile = element.getContainingFile();
                return containingFile == null ? null : containingFile.getName();
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DockerIcon.Companion.getIcon();
            }
        };
    }
}