<template>
  <div id="app">
    <Header/>
    <ProjectForm></ProjectForm>
  </div>
</template>

<script>
  import Header from './components/Header.vue'
  import ProjectForm from "./components/ProjectForm";

  export default {
    name: 'app',
    components: {
      ProjectForm,
      Header
    }
  }
</script>

<style>
  html, body {
    margin: 0px;
    padding: 0px;
    background:#1F1F1F;
  }

  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #2c3e50;

  }

  .mi-container{
    max-width:900px;
    margin:auto;
  }
</style>
