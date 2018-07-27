package com.github.fish.common.utils;

import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.renderer.html.AttributeProvider;

import java.util.Map;

/**
 * 处理标签属性
 */
public class CustomAttributeProvider implements AttributeProvider {

    @Override
    public void setAttributes(Node node, String s, Map<String, String> map) {
        //改变a标签的target为_blank
        if (node instanceof Link){
            map.put("target","_blank");
        }
        //Semantic UI表格样式
        if (node instanceof TableBlock){
            map.put("class","ui celled table");
        }

    }
}
