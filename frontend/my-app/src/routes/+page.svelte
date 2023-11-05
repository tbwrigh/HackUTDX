<script>
    import { withAuth } from "./auth"
    const auth=withAuth()
</script>
<div class="min-h-screen flex flex-col items-center justify-center bg-gradient-to-r from-red-500 via-yellow-500 via-green-500 via-blue-500 via-indigo-500 to-purple-500">
    <h1 class="text-5xl font-bold text-white">
        Welcome to Rainbow Returns!
    </h1>
    <h2 class ="text-2x1 font-bold text-white m-10">
        The Stock Price Portfolio Catered To Your Preferences
    </h2>
    <button class="bg-violet-500 py-2 px-4 rounded m-3" on:click={auth.login}>
        Sign Up/Log In
    </button>
</div>
