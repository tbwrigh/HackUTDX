<script>
    import { onMount } from "svelte";
    import { withAuth } from "./auth"
    let auth = null
    onMount(()=>{
        auth=withAuth()
        console.log(document)
    })

</script>
<div class="min-h-screen flex flex-col items-center justify-center bg-gradient-to-r from-red-500 via-yellow-500 via-green-500 via-blue-500 via-indigo-500 to-purple-500">
    <h1 class="text-8xl font-bold text-white">
        Rainbow Returns
    </h1>
    <h2 class ="text-2xl font-bold text-white m-10">
        Invest in Companies That Match Your Morals!
    </h2>
    <button class="bg-violet-500 py-2 px-4 rounded m-3" on:click={auth.login}>
        Sign Up/Log In
    </button>
</div>
