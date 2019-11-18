<template>
  <div>
    <div class="mi-form-container">
      <div class="container center row">
        <div class="title">Create a new project</div>
        <div class="col l5 m12 s12">
          <div class="subtitle">Configuration</div>
          <div class="row">
            <div class="input-field">
              <input id="groupId" type="text" placeholder="Group ID">
            </div>
          </div>
          <div class="row">
            <div class="input-field">
              <input id="artifactId" type="text" placeholder="Artifact ID">
            </div>
          </div>
          <div class="row">
            <div class="input-field col s12">
              <select id="buildType" type="text">
                <option>Maven</option>
                <option>Gradle</option>
              </select>
            </div>
          </div>
            <a class="more-config">
              More configurations
          </a>
        </div>
        <div class="col l7 m12 s12">
          <div class="subtitle">Additional modules</div>
          <div class="row">
            <div class="input-field col s12">
              <i class="material-icons prefix">search</i>
              <input id="icon_prefix" type="text" class="search" placeholder="Search for a module...">
            </div>
          </div>
        </div>
      </div>
      <div class="container row">
        <div class="s12 m12 l6 mi-validate">
          <div class="btn waves-effect waves-teal">
            <i class="material-icons right">create_new_folder</i>Create Project
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import M from 'materialize-css'

  export default {
    name: 'ProjectForm',
    props: {
      msg: String
    },
    mounted() {
      M.updateTextFields();
      M.FormSelect.init(document.querySelectorAll('select'), null);
    },
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
  .title {
    margin-bottom: 30px;
    font-weight: bold;
    font-size: 30px;
    margin-top:10px;
    color:white;
  }

  .subtitle{
    font-size:20px;
    color:white;
    font-weight: bold;
    text-align:left;
    margin-bottom:10px;
    border-bottom: 1px solid #333;
  }

  .mi-validate{
    text-align:center;
    margin-top:15px;
  }

  .input-field{margin:0 !important;}

  .select-wrapper input.select-dropdown, input {
    color: white !important;
  }

  .select-wrapper .caret {
    fill: white !important;
  }

  ::placeholder { /* Chrome, Firefox, Opera, Safari 10.1+ */
    color: #999;
    opacity: 1; /* Firefox */
  }

  :-ms-input-placeholder { /* Internet Explorer 10-11 */
    color: #999;
  }

  ::-ms-input-placeholder { /* Microsoft Edge */
    color: #999;
  }

  input{
    font-size: 12px !important;
  }

  .more-config:hover{
    cursor:pointer;
    text-decoration: underline;
  }
</style>
