package com.cseunited.alumni.cseunited;

/**
 * Created by Debanik on 19-10-2017.
 * A class representing each item in the discussion forum main page.
 */

class DiscussItem {
    String topText, bottomText;

    /**
     * Constructor to create a DiscussItem object.
     *
     * @param topText    The top text in String type for one post.
     * @param bottomText The bottom text in String type for one post.
     */
    DiscussItem(String topText, String bottomText) {
        this.topText = topText;
        this.bottomText = bottomText;
    }
}
