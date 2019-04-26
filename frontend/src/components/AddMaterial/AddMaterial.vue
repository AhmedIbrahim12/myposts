<template>
  <div id="addMaterial" style="padding: 2% 3% 5% 5%;">
    <v-flex xs12 sm6 md7>
      <v-card class="elevation-12">
        <v-card-text>
          <v-form ref="form">
            <v-textarea
              v-model="form.post"
              prepend-icon="text_fields"
              name="description"
              placeholder="What's on your mind?"
              id="description"
              v-validate="'required'"
              :error-messages="errors.collect('description')"
              data-vv-name="description"
              required
            ></v-textarea>

            <v-checkbox
              v-model="form.private"
              label="Private"
            ></v-checkbox>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" @click="save()">POST</v-btn>
        </v-card-actions>
      </v-card>

      <v-flex style="padding: 2% 0%;" v-for="post in posts" :key="post.id">
        <v-card class="mx-auto" light max-width="500">
          <v-card-title>
            <span class="title font-weight-bold">{{post.user.userName}}</span>
          </v-card-title>

          <v-card-text class="headline font-weight-bold">{{post.content}}</v-card-text>

          <v-card-actions>
            <v-list-tile class="grow">
              <v-list-tile-content>
                <v-list-tile-title v-if="!post.private">Public</v-list-tile-title>
                <v-list-tile-title v-if="post.private">Private</v-list-tile-title>
              </v-list-tile-content>
            </v-list-tile>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-flex>
  </div>
</template>
<script>
import { AXIOS } from "../api-src";
export default {
  name: "AddMaterial",
  data() {
    return {
      form: {
        post: "",
        private: false
      },
      username: "",
      posts: []
    };
  },
  mounted() {
    AXIOS.defaults.headers.common["Authorization"] = this.$session.get(
      "basicAuthHeader"
    );
    this.username = this.$session.get("username");
    this.loadUserPosts();
  },

  methods: {
    save() {
      if (this.isFormValid()) {
        this.doSave();
      }
    },

    doSave() {
      var params = new URLSearchParams();
      params.append("username", this.username);
      params.append("post", this.form.post);
      params.append("isPrivate", this.form.private);
      AXIOS.post("/posts/save", params)
        .then(response => this.loadUserPosts())
        .catch(error => console.log(error.message));
        this.reset();
    },

    loadUserPosts() {
      AXIOS.get("/posts/getUserPosts", {
        params: {
          username: this.username
        }
      })
        .then(response => response.data)
        .then(posts => (this.posts = posts))
        .catch(error => console.log(error.message));
    },

    reset() {
      this.$refs.form.reset();
      this.$validator.reset();
    },

    isFormValid() {
      this.$validator.validateAll();
      return this.fields.description.valid;
    }
  }
};
</script> 