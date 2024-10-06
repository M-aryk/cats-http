package ru.netology;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Main {
    public static final class Post {
        private final String id;
        private final String text;
        private final String type;
        private final String user;
        private final int upvotes;

        public Post(@JsonProperty("id") String id,
                    @JsonProperty("text") String text,
                    @JsonProperty("type") String type,
                    @JsonProperty("user") String user,
                    @JsonProperty("upvotes") int upvotes) {
            this.id = id;
            this.text = text;
            this.type = type;
            this.user = user;
            this.upvotes = upvotes;
        }

        public String id() {
            return id;
        }

        public String text() {
            return text;
        }

        public String type() {
            return type;
        }

        public String user() {
            return user;
        }

        public int upvotes() {
            return upvotes;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Post) obj;
            return Objects.equals(this.id, that.id) &&
                    Objects.equals(this.text, that.text) &&
                    Objects.equals(this.type, that.type) &&
                    Objects.equals(this.user, that.user) &&
                    Objects.equals(this.upvotes, that.upvotes);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, text, type, user, upvotes);
        }

        @Override
        public String toString() {
            return "Post[" +
                    "id=" + id + ", " +
                    "text=" + text + ", " +
                    "type=" + type + ", " +
                    "user=" + user + ", " +
                    "upvotes=" + upvotes + ']';
        }
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client("https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats");

        List<Post> posts = client.sendRequest();
        List<Post> upvotesPosts = posts.stream()
                .filter(x -> x.upvotes > 0)
                .toList();

        for (Post s : upvotesPosts) {
            System.out.println(s);
        }
    }
}