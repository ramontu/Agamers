package dam.agamers.gtidic.udl.agamers.Observers;

/*
public class ScrollToBottom extends RecyclerView.AdapterDataObserver {

    private RecyclerView recycler;
    private MessageAdapter adapter;
    private LinearLayoutManager manager;

    public ScrollToBottom(RecyclerView recycler, MessageAdapter adapter, LinearLayoutManager manager){
        this.recycler = recycler;
        this.adapter = adapter;
        this.manager = manager;

    }

    @Override
    public void onItemRangeInserted(int positionStart, int itemCount){
        super.onItemRangeInserted(positionStart, itemCount);
        int count = adapter.getItemCount();
        int lastVisiblePosition = manager.findLastVisibleItemPosition();
        boolean loanding = (lastVisiblePosition == -1);
        boolean atBottom = (positionStart >= count-1) && (lastVisiblePosition == positionStart -1);

        if (loanding || atBottom){
            recycler.scrollToPosition(positionStart);
        }

    }



}
 */