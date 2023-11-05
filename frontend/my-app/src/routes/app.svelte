<script>
    import { Router, Route } from 'svelte-routing';
  
    import Home from './+page.svelte';
    import Opening from './home/+page.svelte';
    // import SecondPage from './SecondPage.svelte';
  </script>
  
  <Router>
    <Route path="/" component={Opening} />
    <Route path="/home" component={Home} />
  </Router>