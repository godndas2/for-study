<template>
  <section>
    <Menu/>
    <h3>All Users</h3>
    <div class="container">
      <table class="table">
        <thead>
          <tr>
            <th>Id</th>
            <th>Description</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="user in Users" v-bind:key="user.id">
            <td>{{user.id}}</td>
            <td>{{user.description}}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </section>
</template>