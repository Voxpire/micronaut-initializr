<template>
  <div>
    <div class="mi-form-container">
      <div class="container center row">
        <div class="title">Create a new Micronaut project</div>
        <div class="col l5 m12 s12 gray black-text">
          <div class="subtitle">Configuration</div>
          <div class="row">
            <div class="input-field">
              <input id="groupId" type="text" placeholder="Group ID" autocomplete="off">
            </div>
          </div>
          <div class="row">
            <div class="input-field">
              <input id="artifactId" type="text" placeholder="Artifact ID" autocomplete="off">
            </div>
          </div>
          <div class="row">
            <div class="input-field col s12 no-padding">
              <select id="buildType" type="text">
                <option>Maven</option>
                <option>Gradle</option>
              </select>
            </div>
          </div>
          <div class="more-config-container">
            <a class="more-config">
              More configurations
            </a>
          </div>
        </div>
        <div class="col l7 m12 s12">
          <div class="subtitle">Additional modules</div>
          <div class="row">
            <div class="input-field col s12">
              <i class="material-icons prefix">search</i>
              <input id="icon_prefix" type="text" class="search" placeholder="Search for a module...">
              <div v-for="feature in features.slice(0,10)" v-bind:key="feature.name" class="feature">
                <div>
                  <p>
                    <label>
                      <input type="checkbox" class="filled-in" checked="checked" />
                      <span class="feature-name">{{feature.name}}</span>
                    </label>
                  </p>
                </div>
                <div class="feature-description">{{feature.description}}</div>
              </div>
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
  import axios from 'axios';

  export default {
    name: 'ProjectForm',
    data: function () {
      return {
        "features": []
      }
    },
    mounted() {
      M.updateTextFields();
      M.FormSelect.init(document.querySelectorAll('select'), null);
      axios
        .get('http://localhost:8080/features')
        .then(response => {
            this.features = response.data;
          }
        )
    },
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
  .title {
    margin-bottom: 30px;
    font-weight: bold;
    font-size: 30px;
    margin-top: 10px;
    color: white;
  }

  .subtitle {
    font-size: 20px;
    color: white;
    font-weight: bold;
    text-align: left;
    margin-bottom: 10px;
    border-bottom: 1px solid #333;
  }

  .mi-validate {
    text-align: center;
    margin-top: 15px;
  }

  .input-field {
    margin: 0 !important;
  }

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

  input {
    font-size: 12px !important;
  }

  .more-config-container {
    margin-bottom: 20px;
  }

  .more-config:hover {
    cursor: pointer;
    text-decoration: underline;
  }

  .feature {
    padding-left: 20px;
    text-align: left;
    margin-bottom:10px;

  }

  .feature-name{
    line-height: 18px !important;
    font-size:16px !important;
    font-weight:bold;

  }

  .feature-description{
    padding-left:35px;
    margin-top:-15px;
    font-size:13px;
    color:lightgray;
    padding-bottom:8px;
    border-bottom: 1px dashed lightgray;
    margin-bottom:8px;
  }

</style>
