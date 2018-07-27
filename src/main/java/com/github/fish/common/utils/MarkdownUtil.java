package com.github.fish.common.utils;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class MarkdownUtil {

    /**
     * markdown格式内容转成html格式
     *
     * @param markdown
     * @return
     */
    public static String markdownToHtml(String markdown) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }

    /**
     * 增加扩展【标题锚点，表格生成】
     *
     * @param markdown
     * @return
     */
    public static String markdownToHtmlExtensions(String markdown) {
        //H标题id
        Set<Extension> headingAnchorExtension = Collections.singleton(HeadingAnchorExtension.create());
        List<Extension> tablesExtension = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder().extensions(tablesExtension).build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().extensions(headingAnchorExtension).extensions(tablesExtension)
                .attributeProviderFactory(new AttributeProviderFactory() {
                    @Override
                    public AttributeProvider create(AttributeProviderContext attributeProviderContext) {
                        return new CustomAttributeProvider();
                    }
                }).build();
        return renderer.render(document);
    }


}
