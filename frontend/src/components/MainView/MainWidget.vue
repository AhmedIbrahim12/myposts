<template>
  <div id="main">
    <router-view/>
    <v-toolbar color="grey" dark fixed app>
      <v-toolbar-title>My Posts</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-toolbar>
  </div>
</template>
<script>
export default {
  name: "MainWidget",
  data() {
    return {
      drawer: false,
      roles: [],
      selectedItem: "Home"
    };
  }
};
</script>
