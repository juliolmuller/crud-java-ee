<!DOCTYPE html>

<html lang="pt-BR">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>
      SEPT :: Meu TADS
    </title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  </head>
  <body>
    <header class="container-fluid">
      <div style="background: url('https://jjsolutions.net/assets/images/tads-cover-page.jpg') center;">
        <div style="background: rgb(0, 0, 0, 0.8);">
          <div class="container jumbotron text-white font-weight-bold" style="background: rgb(0, 0, 0, 0);">
            <h1 class="display-4">Meu TADS</h1>
            <h4>Tecnologia em Analise e Desenvolvimento de Sistemas</h4>
            <hr class="d-block my-4 bg-white" style="height: 1px;" />
            <p class="lead">Comecando a construir o futuro da humanidade.</p>
          </div>
        </div>
      </div>
    </header>
    <div class="container text-center">
      <h2>Membros da equipe:</h2>
      <main class="card-deck mt-5">
        <%
          String[] equipe = {
            "Andre Antunes",
            "Aurelio Shizuo",
            "Casiano Vidal",
            "Julio Muller",
            "Wesley Caetano"
          };
          String[] links = {
            "https://www.facebook.com/andre.antunes.619",
            "https://www.facebook.com/aurelio.matsunaga",
            "https://www.facebook.com/cassianovidal",
            "https://www.facebook.com/juliolmuller",
            "https://www.facebook.com/D6nero"
          };
          String[] imagens = {
            "https://scontent.fbfh4-1.fna.fbcdn.net/v/t31.0-8/18921196_1428494020541536_2680074679691855060_o.jpg?_nc_cat=109&_nc_oc=AQlqXeJeUqMBWJRj_wBcMIuXNaFfPVndnhqKTM3c_kSk4u9Upe1hB8ixsb3wB8BrYT--w5mUHWaIsc_3AmJoEjdC&_nc_ht=scontent.fbfh4-1.fna&oh=9bf26737620bdfae3056722067b57ebf&oe=5DE456BA",
            "https://scontent.fbfh4-1.fna.fbcdn.net/v/t31.0-8/13403975_812196302215619_7996844070909956502_o.jpg?_nc_cat=110&_nc_oc=AQlne9Y1DgWqdulivfAbXWpTpYfBlHvmGoA7OALQUwGTy3xFLnzmooy74dSE3SnsTFdQn9ZPrFzzD16HT7IoCGCC&_nc_ht=scontent.fbfh4-1.fna&oh=6581ef3f154bba090bcfe3acd0fb957a&oe=5DE1589D",
            "https://scontent.fbfh4-1.fna.fbcdn.net/v/t1.0-9/38689603_2032922856732050_2026960878489829376_n.jpg?_nc_cat=106&_nc_oc=AQnSgIfbYN8YGtB8a5yKz0Dangjth_aLoLZXs86LMLYnfj6dnLOzZf7MwB9HaEUVPdI24B66yrCabJowI5MYltiq&_nc_ht=scontent.fbfh4-1.fna&oh=09894d15aa19eae208d61f9262e9aa8b&oe=5E160707",
            "https://scontent.fbfh4-1.fna.fbcdn.net/v/t1.0-9/10378125_890899474337042_4462428900273186812_n.jpg?_nc_cat=107&_nc_oc=AQnqGGJx4xA5lmpOVpsRJj33oq9UBBt8EMIccfOn7XPD4CzUv7hHqj8ByGS7_ruC-i_CYHslVTrGxdRuNPmFSnJr&_nc_ht=scontent.fbfh4-1.fna&oh=07f84a62939f3a5cc98f7ed9a7d49a44&oe=5DCE3E2A",
            "https://scontent.fbfh4-1.fna.fbcdn.net/v/t1.0-1/c0.3.432.432a/47048014_1827961380659619_4745625071769354240_n.jpg?_nc_cat=100&_nc_oc=AQlOtkuPkgSXs-425gMCdcLWs7I9fGPK3SJS8qK-WFapleX6Zq1D4Nh_SsOP0uqffumcdy8ZzdBlgiYSDuGFE3sD&_nc_ht=scontent.fbfh4-1.fna&oh=2628074cbb5ca3c595f24453aad0c5d9&oe=5E135085"
          };
          for (int i = 0; i < 5; i++) {
        %>
            <div class="card" style="width: 18rem;">
              <img class="card-img-top" src="<% out.print(imagens[i]); %>" alt="Miniatura do membro da equipe">
              <div class="card-body">
                <h5 class="card-title"><% out.print(equipe[i]); %></h5>
              </div>
              <div class="card-footer">
                <a href="<% out.print(links[i]); %>" class="card-link">Facebook</a>
              </div>
            </div>
          <%
            }
          %>
        </main>
        <footer class="mt-5 px-3">
          <a href="<% out.println(request.getContextPath()); %>" class="btn btn-outline-dark">&lt;&lt;&lt; Voltar para a pagina inicial</a>
        </footer>
      </div>
    </div>
  </body>
</html>
