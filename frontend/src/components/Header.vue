<template>
  <div class="mi-header">
    <div class="nav-wrapper">
      <h4 class="mi-title"><b>Micronaut</b> Initializr</h4>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Header',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .mi-title{
    font-weight:normal;
  }

  .mi-header{
    padding:10px 10px 10px 0px;
    width:100%;
    background: black;
    color:#DEDEDE;
    padding:10px 0px 10px 50px;
  }

</style>
