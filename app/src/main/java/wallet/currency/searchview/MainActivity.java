package wallet.currency.searchview;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UsersitemsAdapter adapter;
    private List<UsersItemsModal> itemsList;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        searchView = findViewById(R.id.searchview);


        itemsList = new ArrayList<UsersItemsModal>();
        recyclerView = findViewById(R.id.usersitemsrecycler_view);

        adapter = new UsersitemsAdapter(this, itemsList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new MainActivity.GridSpacingItemDecoration(2, dpToPx(1), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        itemsList.add(new UsersItemsModal("1","item1","500"+" PKR","10"+"% ","1","1000"+" PKR","https://pkgiftshop.com/wp-content/uploads/2020/03/Red-Roses-with-White-Breath-Flowers-bouquet-Price-2400.jpg"));
        itemsList.add(new UsersItemsModal("2","item2","500"+" PKR","10"+"% ","1","1000"+" PKR","https://cdn.shopify.com/s/files/1/1463/4010/t/2/assets/masonry-feature-1-image.jpg?v=6292719477897739107"));
        itemsList.add(new UsersItemsModal("3","item3","500"+" PKR","10"+"% ","1","1000"+" PKR","https://www.cnet.com/a/img/wub4Q7jiOP1L3pAfdNQx1FzSgWw=/940x528/2020/04/17/def1b267-b843-4adf-957f-631062cf5bd7/1800-flowers.jpg"));
        itemsList.add(new UsersItemsModal("4","item4","500"+" PKR","10"+"% ","1","1000"+" PKR","https://www.brides.com/thmb/VtKRUmEbzvqiZ8ockEjO3Ppl03Q=/1500x1000/filters:no_upscale():max_bytes(150000):strip_icc()/most-popular-wedding-flowers-of-all-time-v3-1a6aab594e894422bd02dcd02d654dbc.png"));
        itemsList.add(new UsersItemsModal("5","item5","500"+" PKR","10"+"% ","1","1000"+" PKR","https://www.cnet.com/a/img/wub4Q7jiOP1L3pAfdNQx1FzSgWw=/940x528/2020/04/17/def1b267-b843-4adf-957f-631062cf5bd7/1800-flowers.jpg"));
        itemsList.add(new UsersItemsModal("6","item6","500"+" PKR","10"+"% ","1","1000"+" PKR","https://pkgiftshop.com/wp-content/uploads/2020/03/Red-Roses-with-White-Breath-Flowers-bouquet-Price-2400.jpg"));
        itemsList.add(new UsersItemsModal("7","item7","500"+" PKR","10"+"% ","1","1000"+" PKR","https://www.brides.com/thmb/VtKRUmEbzvqiZ8ockEjO3Ppl03Q=/1500x1000/filters:no_upscale():max_bytes(150000):strip_icc()/most-popular-wedding-flowers-of-all-time-v3-1a6aab594e894422bd02dcd02d654dbc.png"));

        initSearch(searchView);
    }

    private void initSearch(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }



    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)
                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = 20;
                outRect.right = 20;
                outRect.top = 0;
                outRect.bottom = 0;
            }
        }
    }


}