<script>
    import { onMount } from "svelte";
    import { withAuth } from "../auth";

    onMount(async () =>{
        const auth = withAuth();
        await auth.handleRedirectCallback();
    });
</script>
<!-- <h1>
    Loading... This is working properly
</h1> -->
<div class="min-h-screen flex flex-col bg-lime-50">
    <h1 class="items-left justify-top font-bold text-6xl m-6">
        Hello User!
    </h1>
    
</div>