<script lang="ts">
    import { Router, Route } from 'svelte-routing';
    import Home from './+page.svelte';
    import Opening from './home/+page.svelte';

    import type { User } from '@auth0/auth0-spa-js';
    import { withAuth } from './auth';
    import Callback from './callback.svelte';

    const auth = withAuth();

    let user: User;
    auth.user.subscribe(userAuth => {
        user = userAuth;
    });

    let token = "";
    auth.token.subscribe(t => {
        token = t;
    });
</script>

<Router>
    <Route path="/" component={Opening} />
    <Route path="/home" component={Home} />
    <Route path="/callback" component={Callback} />
</Router>

<main>
    <div>
        {#if !user}
            <button on:click={auth.login}>Login</button>
        {:else}
            <button on:click={auth.logout}>Logout</button>
            <h1>Hello {user.nickname}</h1>
            <div class="profile">
                <img class="profile" src={user.picture} alt="User profile">
            </div>
            <p>User:</p>
            <pre>{JSON.stringify(user, null, 2)}</pre>
            <p>Access Token:</p>
            <!-- Don't display access tokens -->
            <pre>DON'T DISPLAY ACCESS TOKENS</pre>
        {/if}
    </div>
</main>