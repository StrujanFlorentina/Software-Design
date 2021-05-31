<template>
  <v-app>
    <v-img src="https://i.pinimg.com/originals/c5/16/ff/c516ff9163fefeaa5974fc7c8855cd02.jpg" app>
      <v-app-bar v-if="(!isAdmin && !isMedic && !isPatient)" color="teal lighten-3" dense dark app>
        <v-toolbar-title>Medical Application</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn icon @click="$router.push({ name:'Home'})">
          <v-icon> mdi-home</v-icon>
        </v-btn>
        <v-btn icon @click="$router.push({ name:'Register'})">
          <v-icon>mdi-account-multiple-outline</v-icon>
        </v-btn>
        <v-btn icon @click="$router.push({ name:'Login'})">
          <v-icon>mdi-login</v-icon>
        </v-btn>
      </v-app-bar>
      <v-main>
       <router-view></router-view>
    </v-main>
    </v-img>
  </v-app>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'App',
  computed: {
    ...mapGetters(['isAdmin']),
    ...mapGetters(['isPatient']),
    ...mapGetters(['isMedic'])
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #186844;
}
</style>
