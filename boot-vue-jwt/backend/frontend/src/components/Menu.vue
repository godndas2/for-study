<template>
  <header>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
      <div>
        <a href="https://github.com/godndas2" class="navbar-brand">BootVue</a>
      </div>
      <ul class="navbar-nav">
        <li>
          <router-link v-if="isAuthenticated" class="nav-link" to="/users">User</router-link>
        </li>
      </ul>
      <ul class="navbar-nav navbar-collapse justify-content-end">
        <li>
          <router-link v-if="!isAuthenticated" class="nav-link" to="/login">Login</router-link>
        </li>
        <li>
          <router-link v-if="isAuthenticated" class="nav-link" to="/logout" >Logout</router-link>
        </li>
      </ul>
    </nav>
  </header>
</template>