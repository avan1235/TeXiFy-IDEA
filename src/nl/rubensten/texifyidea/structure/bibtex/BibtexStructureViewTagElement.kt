package nl.rubensten.texifyidea.structure.bibtex

import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.navigation.NavigationItem
import com.intellij.util.PlatformIcons
import nl.rubensten.texifyidea.psi.BibtexTag
import nl.rubensten.texifyidea.util.evaluate
import nl.rubensten.texifyidea.util.keyName

/**
 * @author Ruben Schellekens
 */
open class BibtexStructureViewTagElement(val tag: BibtexTag) : StructureViewTreeElement, SortableTreeElement {

    val tagPresentation: ItemPresentation = object : ItemPresentation {

        override fun getLocationString() = tag.content.evaluate()

        override fun getPresentableText() = tag.keyName()

        override fun getIcon(b: Boolean) = PlatformIcons.PROTECTED_ICON
    }

    override fun getValue() = tag

    override fun navigate(requestFocus: Boolean) {
        if (tag is NavigationItem) {
            tag.navigate(requestFocus)
        }
    }

    override fun canNavigate(): Boolean {
        return tag is NavigationItem && tag.canNavigate()
    }

    override fun canNavigateToSource(): Boolean {
        return tag is NavigationItem && tag.canNavigateToSource()
    }

    override fun getAlphaSortKey() = tag.keyName() ?: ""

    override fun getPresentation() = tagPresentation

    override fun getChildren(): Array<TreeElement> = emptyArray()
}