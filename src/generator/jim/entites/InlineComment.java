package generator.jim.entites;

import generator.jim.composite.AbstractInline;

public class InlineComment extends AbstractInline {
	
	private static String COMMENT_INLINE_START = "/*";
	private static String COMMENT_INLINE_END = "*/";
	
	private String comment;

	public InlineComment(String comment) {
		this.comment = (comment == null ? "" : comment);
	}
	
	@Override
	public String construct() {		
		return COMMENT_INLINE_START + this.comment + COMMENT_INLINE_END + super.construct();
	}
}
